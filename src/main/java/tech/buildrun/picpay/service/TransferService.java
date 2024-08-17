package tech.buildrun.picpay.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.buildrun.picpay.controller.dto.TransferDto;
import tech.buildrun.picpay.entity.Transfer;
import tech.buildrun.picpay.entity.Wallet;
import tech.buildrun.picpay.exception.InsuficientBalanceException;
import tech.buildrun.picpay.exception.TransferNotAllowedWalletTypeException;
import tech.buildrun.picpay.exception.TransferNotAuthorizedException;
import tech.buildrun.picpay.exception.WalletNotFoundException;
import tech.buildrun.picpay.repository.TransferRepository;
import tech.buildrun.picpay.repository.WalletRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    public TransferService(AuthorizationService authorizationService,
                           NotificationService notificationService,
                           TransferRepository transferRepository,
                           WalletRepository walletRepository) {
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDto transferDto) {

        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));


        validateTransfer(transferDto, sender);
        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());
        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(()-> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedWalletTypeException();
        }

        if (!sender.isBalancerEqualOrGreaterThan(transferDto.value())) {
            throw new InsuficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}

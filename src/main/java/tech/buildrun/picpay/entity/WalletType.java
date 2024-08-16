package tech.buildrun.picpay.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_wallet_type")
@Data
public class WalletType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public WalletType(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public WalletType() {
    }

    public enum Enum{

        USER(1L, "user"),
        MERCHANT(2L, "merchant");

        Enum(long id, String description) {
            this.id = id;
            this.description = description;
        }

        private long id;
        private String description;

        public WalletType get(){
            return new WalletType(id, description);
        }
    }
}

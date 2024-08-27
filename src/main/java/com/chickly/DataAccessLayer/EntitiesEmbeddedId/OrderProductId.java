package com.chickly.DataAccessLayer.EntitiesEmbeddedId;

import jakarta.persistence.Embeddable;
import lombok.*;


@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class OrderProductId {
    @NonNull
    private Integer productId;
    @NonNull
    private Integer orderId;
}

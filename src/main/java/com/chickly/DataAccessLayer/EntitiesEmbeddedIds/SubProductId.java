package com.chickly.DataAccessLayer.EntitiesEmbeddedIds;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
public class SubProductId implements Serializable {
    private int productId;
    private int subProductId;

    public SubProductId() {
    }

    public SubProductId(int productId, int subProductId) {
        this.productId = productId;
        this.subProductId = subProductId;
    }


    // hashCode and equals methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubProductId that = (SubProductId) o;
        return productId == that.productId && subProductId == that.subProductId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, subProductId);
    }
}

package br.com.kproj.salesman.products_catalog.catalog.domain.model.products;

import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.unit.Unit;
import com.trex.shared.annotations.Attribute;
import com.trex.shared.annotations.Model;


@Model
public class Product extends SaleableUnit {

    @Attribute(destinationName = "measurementUnit")
    private Unit unit;

    public Product() {
        super();
    }

    public Product(Long id) {
        super(id);
    }


    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}

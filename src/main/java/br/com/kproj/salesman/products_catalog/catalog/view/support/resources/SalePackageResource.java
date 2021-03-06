package br.com.kproj.salesman.products_catalog.catalog.view.support.resources;

import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackage;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import br.com.uol.rest.infrastructure.annotations.SuperClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collection;

@JsonPropertyOrder({
        "id",
        "saleable",
        "relations",
        "links",
        "uri"
})
@ResourceItem(name="packages", modelReference = SalePackage.class, parent = SaleableResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalePackageResource extends Item {

    private Long id;

    @SuperClass
    private SaleableResource saleable;

    private Collection<SaleableRelationResource> relations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "has-relations")
    public Collection<SaleableRelationResource> getRelations() {
        return relations;
    }

    public void setRelations(Collection<SaleableRelationResource> relations) {
        this.relations = relations;
    }

    @Selectable(expression = "is-a", expandByDefault = true)
    public SaleableResource getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableResource saleable) {
        this.saleable = saleable;
    }

}

package br.com.kproj.salesman.negotiation.proposal.view.dto.session;


import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.negotiation.proposal.view.dto.UpdatePackageItemsDTO;
import br.com.kproj.salesman.negotiation.proposal.view.dto.UpdateQuantityPriceItemsDTO;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value="session")
public class ProposalSaleablesDTO implements Serializable {

    @Autowired
    private SaleableApplication application;

    private Long proposalId;

    private Set<ProposalSaleableItemDTO> proposalSaleableItemDTOs = Sets.newHashSet();

    public Set<ProposalSaleableItemDTO> getProposalSaleableItemDTOs() {
        return proposalSaleableItemDTOs;
    }

    public void setProposalSaleableItemDTOs(Set<ProposalSaleableItemDTO> proposalSaleableItemDTOs) {
        this.proposalSaleableItemDTOs = proposalSaleableItemDTOs;
    }

    public void add(SaleableUnitEntity saleableUnit) {
        Optional<SaleableUnitEntity> result = application.getOne(saleableUnit.getId());

        if (result.isPresent()) {
            ProposalSaleableItemDTO proposalSaleableItemDTO = new ProposalSaleableItemDTO();
            proposalSaleableItemDTO.setSaleableId(result.get().getId());
            proposalSaleableItemDTO.setPrice(result.get().getPrice());
            proposalSaleableItemDTO.setQuantity(1);

            if (SaleableTypeEntity.PACKAGE.equals(result.get().getType())) {
                SalePackageEntity salePackage = (SalePackageEntity) result.get();
                salePackage.getSaleableUnits()
                        .forEach(saleunit ->
                                proposalSaleableItemDTO.addPackageItemDTO(saleunit.getId(), 1, saleunit.getPrice()));
            }

            proposalSaleableItemDTOs.add(proposalSaleableItemDTO);
        }
    }

    public void mergeItems(UpdatePackageItemsDTO item) {

        Optional<SaleableUnitEntity> result = application.getOne(item.getSaleableId());
        if (result.isPresent()) {

            if (SaleableTypeEntity.PACKAGE.equals(result.get().getType())) {
                Optional<ProposalSaleableItemDTO> packageFound = proposalSaleableItemDTOs.stream()
                        .filter(spackage -> item.getSaleableId().equals(spackage.getSaleableId())).findFirst();

                if (!packageFound.isPresent()) {
                    return;
                }
                item.getPackageItems().forEach(itemToMerge -> packageFound.get().updateItem(itemToMerge));
            }
        }
    }

    public void updateRootItem(UpdateQuantityPriceItemsDTO dto) {
        Optional<SaleableUnitEntity> result = application.getOne(dto.getSaleableId());

        if(result.isPresent()) {
            Optional<ProposalSaleableItemDTO> itemFound = proposalSaleableItemDTOs.stream().filter(item ->
                    item.getSaleableId().equals(dto.getSaleableId())).findFirst();

            if(itemFound.isPresent()) {
               itemFound.get().updateRootItem(dto);
            }
        }
    }

    public void deleteRootItem(Long saleableId) {
        this.proposalSaleableItemDTOs.remove(new ProposalSaleableItemDTO(saleableId));
    }

    public Optional<ProposalSaleableItemDTO> getByPackageId(Long packageId) {
        return this.proposalSaleableItemDTOs.stream().filter(dto -> packageId.equals(dto.getSaleableId())).findFirst();
    }

    /**
     * Usado para carregar os produtos de uma compra que ja esta salva
     *
     */
    public void load(List<ProposalSaleableItem> saleableItems) {

        List<ProposalSaleableItem> onlyPackages = saleableItems.stream()
                    .filter(filterPackage -> filterPackage.hasPackage()).collect(Collectors.toList());

        onlyPackages.stream().forEach(itemPackage ->
                proposalSaleableItemDTOs.add(ProposalSaleableItemDTO.create(itemPackage)));


        for (ProposalSaleableItem item: saleableItems) {
            if (item.hasPackage()) continue;
            if (item.hasSaleableWithPackage()) {
                Optional<ProposalSaleableItemDTO> result = proposalSaleableItemDTOs.stream()
                        .filter(packageItem -> packageItem.getSaleableId().equals(item.getSalePackage().getId())).findFirst();
                result.get().addPackageItemDTO(item.getId(), item.getSaleableUnit().getId(), item.getQuantity(), item.getPrice());
            } else {
                this.proposalSaleableItemDTOs.add(ProposalSaleableItemDTO.create(item));
            }
        }

    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setApplication(SaleableApplication application) {
        this.application = application;
    }

    public void clear() {
        this.proposalId = null;
        this.proposalSaleableItemDTOs.clear();
    }



}
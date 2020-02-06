package gov.tn.taxecommune.service.searching;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Component
public class TaxationSearchParameters {
    private String taxationsProperty;
    private String propertyValue;
    private Integer pageSize;
    private Integer page;

    public Optional<String> getTaxationsProperty() {
        return Optional.ofNullable(taxationsProperty);
    }

    public Optional<String> getPropertyValue() {
        return Optional.ofNullable(propertyValue);
    }

    public Optional<Integer> getPageSize() {
        return Optional.ofNullable(pageSize);
    }

    public Optional<Integer> getPage() {
        return Optional.ofNullable(page);
    }
}

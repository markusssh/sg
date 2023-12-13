package dev.sg.DTOs.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Integer id;
    private String name;

    /**
     * 0 if none
     */
    private Integer parentId;

}

package entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Title implements Serializable {

    @JsonProperty("title_full")
    private String titleFull;


    //
//    @JsonCreator
//    public Title (@JsonProperty("title_full") String titleFull) {
//        this.titleFull = titleFull;
//    }

}

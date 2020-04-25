package pl.kniziol.coderbook.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageInformation {

    private String message;
    private String cssStyle;
    private String messageStatus;

}

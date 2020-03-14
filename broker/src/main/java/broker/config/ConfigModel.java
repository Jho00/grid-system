package broker.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfigModel {
    private String serverPort;
    private String serverHost;

    private String peerPort;
    private String peerHost;
}

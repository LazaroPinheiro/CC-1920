import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnonGwCloud {
    private Map<String, byte[]> ficheiros;

    public AnonGwCloud(){
        ficheiros = new HashMap<>();
    }

    public synchronized void inserirFicheiro (String address, byte[] ficheiro){
        ficheiros.put(address,ficheiro);
    }

    public synchronized byte[] getFicheiro(String address){
        byte[] ficheiro = ficheiros.get(address).clone();
        ficheiros.remove(address);
        return ficheiro;
    }

}
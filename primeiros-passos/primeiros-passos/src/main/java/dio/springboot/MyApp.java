package dio.springboot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotations.Autowired;

@Component
public class MyApp implements CommandLineRunner {
    @Autowired
    private Calculadora calculadora;

    @Override
    public void run(String... args) throws Exception{
        System.out.println("O resultado é " + calculadora.somar(2,7));
    }
}

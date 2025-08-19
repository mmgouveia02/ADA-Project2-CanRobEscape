import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int width = Integer.parseInt(line[1]);
        int diameter = Integer.parseInt(reader.readLine());
        int numlasers = Integer.parseInt(reader.readLine());
        Solver solver = new Solver(width, diameter, numlasers);

        for(int i = 0; i < numlasers; i++){
            line = reader.readLine().split(" ");
            solver.addLaser(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
        reader.close();

        System.out.println(solver.isPossible()?"Rob manages to escape!":"Impossible to escape");
    }
}
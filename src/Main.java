import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        List<User> users = generateUsers(5);
        System.out.println(generateUserSql(users));
        Map<UUID, List<UUID>> edges = generateGraph(users);
        Graph g = new Graph(edges);
//        for(Map.Entry<UUID, List<UUID>> entry :edges.entrySet()) {
//            System.out.println(entry.getKey()+" * "+entry.getValue());
//        }
        System.out.println("\nНачало поиска: "+users.get(0).getId()+"\n");
        g.dfs(users.get(0).getId());
        System.out.println();
        g.bfs(users.get(0).getId());
    }
    public static Map<UUID, List<UUID>>generateGraph(List<User> users) {
        UUID from; UUID to;
        int i; int ver;
        Map<UUID, List<UUID>> edges=new HashMap<>();
        Graph g = new Graph(edges);
        Random r = new Random();
        for (User user: users) {
            from = user.getId();
            i = users.indexOf(user);
            for (int j = i+1; j<users.size(); j++){
                ver=r.nextInt(11);
                if (ver < 4)
                    to = from;
                else
                    to = users.get(j).getId();
                g.addEdge(from,to);
                if (ver > 6) {
                   g.addEdge(to,from);
                }
            }
        }
        return edges;
    }
    public static List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream("nickPass.csv");
             Scanner scanner = new Scanner(inputStream)) {
            List<String> nickName = new ArrayList<>();
            List<String> password = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String[] word = scanner.nextLine().split(",");
                nickName.add(word[0]);
                password.add(word[1]);
            }

            for (int i = 0; i < count; i++) {
                users.add(new User(UUID.randomUUID(), nickName.get(i),
                        password.get(i), generateDayOfBirth()));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
    public static LocalDateTime generateDayOfBirth() {
        Random r = new Random();
        int month= r.nextInt(12-1)+1;
        int year = r.nextInt(2005-1960)+1970;
        LocalDate localDate = LocalDate.of(year,month,1);
        int countDay = localDate.plusMonths(1)
                .plusDays(-1).getDayOfMonth();
        int day =r.nextInt(countDay -1)+1;
        return LocalDateTime.of(year, month, day, 0, 0, 0);
    }
    public static String generateUserSql(List<User> users) {
        try (PrintWriter pw = new PrintWriter("User.sql")){
            char coma = ',';
            int i= 0;
            pw.println("INSERT INTO users(id,nickname,hashed_password,date_of_birth) VALUES");
            for (User user : users) {
                if(i == users.size()-1)
                    coma = ';';
                pw.println("('" + user.getId() + "','" + user.getNickname() + "','" +
                        user.getPassword() + "','" + user.getDayOfBirth() + "')" + coma);
                i++;
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return "\nList of Users and User.sql has been successfully created.";
    }
}
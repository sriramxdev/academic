import java.io.*;

public class FileMergeDemo {
    public static void main(String[] args) {
        try (
            BufferedReader br1 = new BufferedReader(new FileReader("file1.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("file2.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("merged.txt"))
        ) {
            String line;
            while ((line = br1.readLine()) != null) bw.write(line + "\n");
            while ((line = br2.readLine()) != null) bw.write(line + "\n");
            System.out.println("Files merged successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

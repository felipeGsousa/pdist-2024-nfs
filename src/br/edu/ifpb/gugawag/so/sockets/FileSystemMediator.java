package br.edu.ifpb.gugawag.so.sockets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSystemMediator {

    String OSName;
    String sep;
    String missingCMD = "Digite algum dos comandos a seguir: readdir, rename, remove, create";
    String HOME;
    public FileSystemMediator() {
        OSName = System.getProperty("os.name");
        if (OSName.startsWith("Windows")) sep = "\\";
        else sep = "/";
        HOME = System.getProperty("user.home");
    }

    public String mediator(String params) throws IOException {
        String[] values = params.split(" ");

        switch (values[0]) {
            case "readdir":
                return readdir();
            case "remove":
                if (values.length == 2) {
                    return remove(values[1]);
                } else {
                    return "Insira o nome do arquivo a ser removido";
                }
            case "create":
                if (values.length == 2) {
                    return create(values[1]);
                } else {
                    return "Insira o nome do arquivo a ser criado";
                }
            case "rename":
                if (values.length == 3) {
                    return rename(values[1], values[2]);
                } else {
                    return "Insira o nome do arquivo a ser alterado e o novo nome";
                }
            default:
                return missingCMD;
        }
    }

    private String readdir () {
        String val = Stream.of(new File(System.getProperty("user.home")).list()).collect(Collectors.joining(" | ")).toString();
        return val;
    }
    private String rename (String arq, String newName) throws IOException {
        Path file = Paths.get(HOME+sep+arq);
        if (Files.exists(file)) {
            Files.move(file, file.resolveSibling(newName),
                    StandardCopyOption.REPLACE_EXISTING);
            return "Nome do arquivo " + arq + " alterado para: " + newName;
        } else {
            return "Arquivo não existe";
        }
    }
    private String create (String arqName) throws IOException {
        Path file = Paths.get(HOME+sep+arqName);
        if (!Files.exists(file)) {
            Files.createFile(file);
            return "Arquivo " + arqName + " criado";
        } else {
            return "Arquivo já existe";
        }
    }
    private String remove (String arq) throws IOException {
        Path file = Paths.get(HOME+sep+arq);
        if (Files.exists(file)) {
            Files.delete(file);
            return "Arquivo " + arq + " removido";
        } else {
            return "Arquivo não existe";
        }
    }
}

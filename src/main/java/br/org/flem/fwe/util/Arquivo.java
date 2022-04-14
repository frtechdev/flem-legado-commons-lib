/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.fwe.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;

/**
 *
 * @author mjpereira
 */
public class Arquivo {

    public static boolean salvar(String local, byte[] fileData, String nome) throws FileNotFoundException, IOException {
        FileOutputStream out = null;
        File f = new File(local + nome);
        if (f.exists()) {
            return false;
        } else {
            out = new FileOutputStream(f);
            out.write(fileData);
            out.flush();
            out.close();
        }
        return true;
    }

    public static boolean deletarESalvar(String local, byte[] fileData, String nome) throws FileNotFoundException, IOException {
        FileOutputStream out = null;
        File f = new File(local + nome);
        if (f.exists()) {
            f.delete();
        }
        out = new FileOutputStream(f);
        out.write(fileData);
        out.flush();
        out.close();

        return true;
    }

    public static File[] listarArquivosDiretorio(String local) {
        File f = new File(local);
        File[] arquivos = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String arquivo = pathname.getName().toLowerCase();
                return ((arquivo.endsWith(".gif") || (arquivo.endsWith(".jpg")) || (arquivo.endsWith(".jpeg")) || (arquivo.endsWith(".png")) || (arquivo.endsWith(".bmp")) ));
            }
        });
        return arquivos;
    }

    public static File[] listFilesAsArray(File directory, FilenameFilter filter, boolean recurse) {
        Collection<File> files = listFiles(directory, filter, recurse);
        File[] arr = new File[files.size()];
        return files.toArray(arr);
    }

    public static Collection<File> listFiles(File directory, FilenameFilter filter, boolean recurse) {
        Vector<File> files = new Vector<File>();
        File[] entries = directory.listFiles();
        for (File entry : entries) {
            if (filter == null || filter.accept(directory, entry.getName())) {
                files.add(entry);
            }
            if (recurse && entry.isDirectory()) {
                files.addAll(listFiles(entry, filter, recurse));
            }
        }
        return files;
    }

    public static String extensao(File file) {
        String resp = "";
        String nome = file.getName();
        int i = nome.lastIndexOf(".");
        if (i != -1) {
            resp = nome.substring(i);
        }
        return resp;
    }

    public static String tipo(String nome) {
        String resp = "";
        int i = nome.lastIndexOf(".");
        if (i != -1) {
            resp = nome.substring(i+1);
        }
        return resp.toUpperCase();
    }

}

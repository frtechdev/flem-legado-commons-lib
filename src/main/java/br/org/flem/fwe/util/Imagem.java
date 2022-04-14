/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.fwe.util;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;

/**
 *
 * @author mjpereira
 */
public class Imagem {

    public static BufferedImage carregarImagem(String ref) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(ref));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimg;
    }

    public static BufferedImage redimensionar(byte[] imagem, int novaLargura, int novaAltura, boolean manterProporcao) {
        return redimensionar(obterBufferedImage(imagem), novaLargura, novaAltura, manterProporcao);
    }

    public static BufferedImage redimensionar(BufferedImage bi, int novaLargura, int novaAltura, boolean manterProporcao) {
        double thumbRatio = (double) novaLargura / (double) novaAltura;
        int altura =bi.getHeight(null);
        int largura =bi.getWidth(null);
        double imageRatio = (double) largura / (double) altura;
          if (manterProporcao) {
            if (thumbRatio < imageRatio) {
                novaAltura = (int) (novaLargura / imageRatio);
            } else {
                novaLargura = (int) (novaAltura * imageRatio);
            }
        }

        Image i = bi.getScaledInstance(novaLargura, novaAltura, Image.SCALE_SMOOTH);
        BufferedImage bie = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = bie.getGraphics();
        g.drawImage(i, 0, 0, null);
        g.dispose();
        return bie;
    }

    public static BufferedImage redimensionar(Image img, int novaLargura, int novaAltura, boolean manterProporcao) {
        double thumbRatio = (double) novaLargura / (double) novaAltura;
        int largura = img.getWidth(null);
        int altura = img.getHeight(null);
        double imageRatio = (double) largura / (double) altura;
        if (manterProporcao) {
            if (thumbRatio < imageRatio) {
                novaAltura = (int) (novaLargura / imageRatio);
            } else {
                novaLargura = (int) (novaAltura * imageRatio);
            }
        }

        BufferedImage dimg = dimg = new BufferedImage(novaLargura, novaAltura, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, novaLargura, novaAltura, 0, 0, largura, altura, null);
        g.dispose();
        return dimg;
    }

     public static void salvarImagem(BufferedImage img, String arquivo) {
        BufferedOutputStream out;
        String tipo = Arquivo.tipo(arquivo);
        try {
            out = new BufferedOutputStream(new FileOutputStream(arquivo));
            ImageIO.write(img, tipo, out);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    public static BufferedImage obterBufferedImage(byte[] imagem) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new ByteArrayInputStream(imagem));
        } catch (IOException ex) {
            Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bi;
    }

    public static Image obterImagem(byte[] imagem, String descricao) {
        return new ImageIcon(imagem, descricao).getImage();
    }

}

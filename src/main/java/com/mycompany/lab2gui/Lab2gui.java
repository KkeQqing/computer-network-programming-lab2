/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lab2gui;

/**
 *
 * @author KQ
 */
public class Lab2gui {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // 强制使用 Nimbus 主题（确保所有环境一致）
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("无法设置 Nimbus 主题：" + e.getMessage());
        }

        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}

package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import dao.UserDao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class HomeView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel home;
    
    public HomeView() {
        setTitle("Lista de compras - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 500);  // Tamanho da tela
        home = new JPanel();
        home.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(home);
        
        GroupLayout gl_home = new GroupLayout(home);
        gl_home.setAutoCreateContainerGaps(true);  // Adiciona espaços automáticos entre os componentes
        home.setLayout(gl_home);

        JLabel title = new JLabel("BEM VINDO A LISTAS DE COMPRAS!");
        title.setFont(new Font("Tahoma", Font.PLAIN, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HomeView.this.dispose(); // Fecha a tela de login
        	    new LoginView().setVisible(true); // Abre a tela de login
        	}
        });

        JButton registerButton = new JButton("Cadastro");
        registerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HomeView.this.dispose(); // Fecha a tela de login
        		
                UserDao userDao = new UserDao();
                UserController userController = new UserController(userDao);
                
                RegistrationUserView registrationView = new RegistrationUserView(userController);
                registrationView.setVisible(true); // Abre a tela de cadastro
        	}
        });

        // Centralizando os componentes na tela
        gl_home.setHorizontalGroup(
            gl_home.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)  // Garante que o conteúdo não encoste nas bordas
                .addGroup(gl_home.createParallelGroup(Alignment.CENTER)
                    .addComponent(title)
                    .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(200, Short.MAX_VALUE)  // Garante que o conteúdo não encoste nas bordas
        );

        // Vertical grouping for layout
        gl_home.setVerticalGroup(
            gl_home.createSequentialGroup()
                .addGap(80)  // Espaço no topo
                .addComponent(title)
                .addGap(40)
                .addComponent(loginButton)
                .addGap(18)
                .addComponent(registerButton)
                .addContainerGap(80, Short.MAX_VALUE)  // Espaço na parte inferior
        );
    }
}

package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import controller.UserController;
import dao.UserDao;

public class LoginView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel login;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginView() {
        setTitle("Lista de compras - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        setLocationRelativeTo(null);
        
        login = new JPanel();
        login.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(login);
        
        GroupLayout gl_login = new GroupLayout(login);
        gl_login.setAutoCreateContainerGaps(true);
        login.setLayout(gl_login);

        JLabel title = new JLabel("LOGIN");
        title.setFont(new Font("Tahoma", Font.PLAIN, 22));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        emailField = new JTextField();
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        emailField.setColumns(20);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passwordField.setColumns(20);
        
        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	LoginView.this.dispose(); // Fecha a tela de login
			    new HomeView().setVisible(true);
            }
        });

        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginView.this,
                            "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                    JOptionPane.showMessageDialog(LoginView.this,
                            "Por favor, insira um e-mail válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.length() < 6) {
                    JOptionPane.showMessageDialog(LoginView.this,
                            "A senha deve ter pelo menos 6 caracteres!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                UserDao userDao = new UserDao();
                UserController userController = new UserController(userDao);
                
                try {
					if (userController.loginUserController(email, password)) {
						LoginView.this.dispose(); // Fecha a tela de login
					    new UserView(userController).setVisible(true); // Abre a tela do user
					} else {
	                    JOptionPane.showMessageDialog(LoginView.this,
	                            "Senha ou email incorreto!", "Erro", JOptionPane.ERROR_MESSAGE);
	                    return;
					}
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
					System.out.println("Erro ao verificar email e senha: " + e1.getMessage());
				}
                
                emailField.setText("");
                passwordField.setText("");
                
                
            }
        });

        // Configuração do layout para centralizar os componentes
        gl_login.setHorizontalGroup(
            gl_login.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addGroup(gl_login.createParallelGroup(Alignment.CENTER)
                    .addComponent(title)
                    .addGroup(gl_login.createSequentialGroup()
                        .addGroup(gl_login.createParallelGroup(Alignment.TRAILING)
                            .addComponent(emailLabel)
                            .addComponent(passwordLabel))
                        .addGap(10)
                        .addGroup(gl_login.createParallelGroup(Alignment.LEADING)
                            .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                    .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(200, Short.MAX_VALUE)
        );

        gl_login.setVerticalGroup(
            gl_login.createSequentialGroup()
                .addGap(80)
                .addComponent(title)
                .addGap(40)
                .addGroup(gl_login.createParallelGroup(Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addGroup(gl_login.createParallelGroup(Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30)
                .addComponent(loginButton)
                .addGap(40)
                .addComponent(backButton)
                .addContainerGap(80, Short.MAX_VALUE)
        );
    }
}
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import controller.UserController;

public class RegistrationUserView extends JFrame {

	private UserController userController;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nameField;
    private JTextField nickNameField;
    private JTextField emailField;
    private JPasswordField passwordField; // Campo de senha protegido

    public RegistrationUserView(UserController userController) {
    	this.setUserController(userController);
    	
        setTitle("WishList");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel title = new JLabel("CADASTRO DE USUÁRIO");
        title.setFont(new Font("Tahoma", Font.PLAIN, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nameLabel = new JLabel("Nome completo:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel nickNameLabel = new JLabel("NickName:");
        nickNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameField.setColumns(10);

        nickNameField = new JTextField();
        nickNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nickNameField.setColumns(10);

        emailField = new JTextField();
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        emailField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passwordField.setColumns(10);

        JButton sendButton = new JButton("ENVIAR");
        sendButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        sendButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String nickName = nickNameField.getText().trim();
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Validação de campos obrigatórios
                if (name.isEmpty() || nickName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(RegistrationUserView.this, 
                        "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validação de formato de e-mail
                if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                    JOptionPane.showMessageDialog(RegistrationUserView.this, 
                        "Por favor, insira um e-mail válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validação de senha (mínimo de 6 caracteres como exemplo)
                if (password.length() < 6) {
                    JOptionPane.showMessageDialog(RegistrationUserView.this, 
                        "A senha deve ter pelo menos 6 caracteres!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Chamando o controller para registrar o usuário
                String success = userController.registerUserController(name, nickName, email, password);
                
                if (success.equals("Sucesso!")) {
                    JOptionPane.showMessageDialog(RegistrationUserView.this, 
                            "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(RegistrationUserView.this, 
                            success, "Erro", JOptionPane.ERROR_MESSAGE);
                }

                // Limpar os campos após o envio
                nameField.setText("");
                nickNameField.setText("");
                emailField.setText("");
                passwordField.setText("");
            }
        });


        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(32)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nickNameLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nickNameField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(57, Short.MAX_VALUE))
                .addGroup(Alignment.CENTER,
                    gl_contentPane.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.CENTER)
                            .addComponent(title, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
        );

        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(27)
                    .addComponent(title)
                    .addGap(45)
                    .addComponent(nameLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(nickNameLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(nickNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(emailLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(passwordLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(55)
                    .addComponent(sendButton)
                    .addContainerGap(177, Short.MAX_VALUE))
        );

        contentPane.setLayout(gl_contentPane);
    }

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}
}

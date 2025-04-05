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

import controller.ItemController;
import controller.UserController;

public class AddItemView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel addItemListView;
    private JTextField nameField;
    private JTextField quantityField;

    public AddItemView(ItemController itemController, UserController userController, Integer user_id) {
    	
        setTitle("Lista de compras - Adicionar item");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        setLocationRelativeTo(null); 
        
        addItemListView = new JPanel();
        addItemListView.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(addItemListView);

        JLabel title = new JLabel("ADICIONAR UM NOVO ITEM");
        title.setFont(new Font("Tahoma", Font.PLAIN, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nameLabel = new JLabel("Nome do item:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameField.setColumns(10);

        JLabel quantityLabel = new JLabel("Quantidade:");
        quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        quantityField = new JTextField();
        quantityField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        quantityField.setColumns(10);
        
        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AddItemView.this.dispose(); // Fecha a tela de adicionar item
			    new ItemListView(itemController, userController, user_id).setVisible(true);
            }
        });

        JButton sendButton = new JButton("ENVIAR");
        sendButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        sendButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                
                Integer quantity = null;
                try {
                    quantity = Integer.parseInt(quantityField.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddItemView.this, 
                        "Quantidade deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validação de campos obrigatórios
                if (name.isEmpty() || quantity == null) {
                    JOptionPane.showMessageDialog(AddItemView.this, 
                        "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                ItemController itemController = new ItemController();
                if (itemController.addItemController(name, quantity, user_id)) {
                	AddItemView.this.dispose(); // Fecha a tela de adicionar item
    			    new ItemListView(itemController, userController, user_id).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(AddItemView.this, 
                            "Algo deu errado!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                }
                
                // Limpar os campos após o envio
                nameField.setText("");
                quantityField.setText("");
            }
        });


        GroupLayout gl_contentPane = new GroupLayout(addItemListView);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(32)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
                        .addComponent(quantityLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(quantityField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(57, Short.MAX_VALUE))
                .addGroup(Alignment.CENTER,
                    gl_contentPane.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.CENTER)
                            .addComponent(title, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(quantityLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(quantityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(sendButton)
                    .addGap(10)
                    .addComponent(backButton)
                    .addContainerGap(177, Short.MAX_VALUE))
        );

        addItemListView.setLayout(gl_contentPane);
    }
}

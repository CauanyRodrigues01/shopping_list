package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ItemController;
import controller.UserController;
import model.ItemModel;

public class EditItemView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel editItem;
	private JTextField nameField;
	private JTextField quantityField;
	private JTextField isCompletedField;

	public EditItemView(UserController userController, Integer userId, Integer itemId) {

		setTitle("Lista de compras - edição de item");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		setLocationRelativeTo(null);

		editItem = new JPanel();
		editItem.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(editItem);

		JLabel title = new JLabel("EDIÇÃO DE ITEM");
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

		JLabel isCompletedLabel = new JLabel("Comprado (true ou false):");
		isCompletedLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		isCompletedField = new JTextField();
		isCompletedField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		isCompletedField.setColumns(10);

		ItemController itemController = new ItemController();
		ItemModel item = itemController.getItemByIdController(itemId, userId);

		if (item != null) {
			nameField.setText(item.getName());
			quantityField.setText(String.valueOf(item.getQuantity()));
			isCompletedField.setText(String.valueOf(item.getIsCompleted()));
		} else {
			JOptionPane.showMessageDialog(this, "Item não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}

		JButton sendButton = new JButton("ENVIAR");
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText().trim();
				String quantityText = quantityField.getText().trim();
				String isCompletedText = isCompletedField.getText().trim();

				Boolean isCompleted = null;
				if (isCompletedText.equalsIgnoreCase("true") || isCompletedText.equalsIgnoreCase("false")) {
					isCompleted = Boolean.parseBoolean(isCompletedText);
				} else {
					JOptionPane.showMessageDialog(EditItemView.this, "O campo 'Comprado' deve ser true ou false!",
							"Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Integer quantityInt = null;
				try {
					quantityInt = Integer.parseInt(quantityField.getText().trim());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(EditItemView.this, "Quantidade deve ser um número válido!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validação de campos obrigatórios
				if (name.isEmpty() || quantityText.isEmpty() || isCompletedText.isEmpty()) {
					JOptionPane.showMessageDialog(EditItemView.this, "Por favor, preencha todos os campos!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente editar o item \"" + name + "\"?",
						"Confirmação de Remoção", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {

					ItemModel item = new ItemModel();
					item.setId(itemId);
					item.setName(name);
					item.setQuantity(quantityInt);
					item.setUser_id(userId);
					item.setIsCompleted(isCompleted);

					boolean isEdit = itemController.editItemController(item);

					if (isEdit) {
						nameField.setText("");
						quantityField.setText("");
						isCompletedField.setText("");

						JOptionPane.showMessageDialog(EditItemView.this, "Item atualizado com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						EditItemView.this.dispose();
						new ItemListView(itemController, userController, userId).setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null,
								"Não foi possível editar o item. Verifique se ele ainda existe ou pertence a você.");
					}

				}

			}
		});

		JButton backButton = new JButton("Voltar");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditItemView.this.dispose(); // Fecha a tela de login
				new ItemListView(itemController, userController, userId).setVisible(true);
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(editItem);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(32)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
						.addComponent(quantityLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(quantityField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
						.addComponent(isCompletedLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(isCompletedField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
						.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(57, Short.MAX_VALUE))
				.addGroup(Alignment.CENTER, gl_contentPane.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.CENTER)
								.addComponent(title, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
								.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGap(0, 0, Short.MAX_VALUE)));

		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(27).addComponent(title).addGap(45)
						.addComponent(nameLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(quantityLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(quantityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(isCompletedLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(isCompletedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(55).addComponent(sendButton).addGap(18).addComponent(backButton)
						.addContainerGap(177, Short.MAX_VALUE)));

		editItem.setLayout(gl_contentPane);
	}
}

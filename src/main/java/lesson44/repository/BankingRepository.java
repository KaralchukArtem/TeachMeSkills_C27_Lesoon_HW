package lesson44.repository;

import lesson44.model.CardModel;
import lesson44.model.ClientModel;
import lesson44.pack.PostgresDriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BankingRepository {
    @Autowired
    public PostgresDriverManager postgresDriverManager;
    private int COUNTER = 0;

    public ClientModel getClientById(int id) {
        String sql = """
                SELECT client.client_id,client.name,card.number,card.card_id,card.balance FROM client
                JOIN card ON client.client_id = card.client_id
                WHERE client.client_id = ?;
                """;
        PreparedStatement preparedStatement;
        ResultSet preparedResultSet;
        try (Connection connection = postgresDriverManager.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            ClientModel clientModel = new ClientModel();
            List<CardModel> cards = new ArrayList<>();

            while (resultSet.next()) {
                clientModel.setId(resultSet.getInt("client_id"));
                clientModel.setName(resultSet.getString("name"));

                CardModel card = new CardModel();
                card.setId(resultSet.getInt("card_id"));
                card.setCardNumber(resultSet.getString("number"));
                card.setCardBalance(resultSet.getInt("balance"));
                cards.add(card);
            }
            if (clientModel != null) {
                clientModel.setCards(cards);
                return clientModel;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ex!");
        }
        return null;
    }
}

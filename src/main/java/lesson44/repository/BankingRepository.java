package lesson44.repository;

import lesson44.dao.TransferCardDTO;
import lesson44.model.CardModel;
import lesson44.model.ClientModel;
import lesson44.pack.PostgresDriverManager;
import lesson44.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BankingRepository {
    @Autowired
    public PostgresDriverManager postgresDriverManager;
    @Autowired
    public Validator validator;

    public ClientModel getClientById(int id) {
        String sql = """
                SELECT client.client_id,client.name,card.number,card.card_id,card.balance FROM client
                JOIN card ON client.client_id = card.client_id
                WHERE client.client_id = ?;
                """;
        PreparedStatement preparedStatement;
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
            if (validator.idClientModelValid(clientModel)) {
                clientModel.setCards(cards);
                return clientModel;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ex!");
        }
        return null;
    }

    public void transfer(TransferCardDTO cardDTO) {
        try (Connection connection = postgresDriverManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT balance FROM card WHERE client_id = ? AND number = ?");
            preparedStatement.setInt(1, cardDTO.getClientId());
            preparedStatement.setString(2, cardDTO.getCardFrom());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new SQLException("Карта не найдена");
            }


            BigDecimal balanceFrom = resultSet.getBigDecimal("balance");
            BigDecimal transferAmount = cardDTO.getAmount();

            if (balanceFrom.compareTo(transferAmount) < 0) {
                throw new SQLException("На карте не достаточно средств");
            }

            BigDecimal newBalanceCard = balanceFrom.subtract(transferAmount);
            String sqlUpdateBalanceCard = "UPDATE card SET balance = ? WHERE client_id = ? AND number = ?";
            PreparedStatement preparedStatementUpdateBalance = connection.prepareStatement(sqlUpdateBalanceCard);
            preparedStatementUpdateBalance.setBigDecimal(1, newBalanceCard);
            preparedStatementUpdateBalance.setInt(2, cardDTO.getClientId());
            preparedStatementUpdateBalance.setString(3, cardDTO.getCardFrom());
            preparedStatementUpdateBalance.executeUpdate();

            String sqlCheckCardTo = "SELECT balance FROM card WHERE client_id = ? AND number = ?";
            PreparedStatement preparedStatementCheckCardTo = connection.prepareStatement(sqlCheckCardTo);
            preparedStatementCheckCardTo.setInt(1, cardDTO.getClientId());
            preparedStatementCheckCardTo.setString(2, cardDTO.getCardTo());
            ResultSet resultSetTo = preparedStatementCheckCardTo.executeQuery();

            if (!resultSetTo.next()) {
                throw new SQLException("Карта получателя не найдена");
            }

            String sqlUpdateTo = "UPDATE card SET balance = balance + ? WHERE client_id = ? AND number = ?";
            PreparedStatement preparedStatementUpdateTo = connection.prepareStatement(sqlUpdateTo);
            preparedStatementUpdateTo.setBigDecimal(1, transferAmount);
            preparedStatementUpdateTo.setInt(2, cardDTO.getClientId());
            preparedStatementUpdateTo.setString(3, cardDTO.getCardTo());
            preparedStatementUpdateTo.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ex!");
        }
    }


}

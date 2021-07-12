package me.igormgs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import org.bukkit.entity.Player;

import me.igormgs.Main;

public class MySQLManager {
	
	  private static MySQL db;
	  public Main plugin;
	  
	  public MySQLManager(Main plugin2) {
		  this.plugin = plugin2;
	  }
	  
	  public void setupSQL()
	    throws SQLException{
	      db = new MySQL(this.plugin, 
	        Main.plugin.getConfig().getString("mysql.host-name"), 
	        Main.plugin.getConfig().getString("mysql.porta"), 
	        Main.plugin.getConfig().getString("mysql.database"), 
	        Main.plugin.getConfig().getString("mysql.usuario"), 
	        Main.plugin.getConfig().getString("mysql.senha"));
	      db.openConnection();
	      Statement statement = db.getConnection().createStatement();
	      statement.executeUpdate("CREATE TABLE IF NOT EXISTS `dados_lobby` (`uuid` varchar(255), `username` varchar(255), `senha` varchar(255), `rank` int(2), `xp` int(16), `coins` int(16), `verificado` TEXT, `staffchat` int(16), `tell` int(16))");
	      statement.executeUpdate("CREATE TABLE IF NOT EXISTS `inf_extr_sv` (`status_chat` INT, `nick` varchar(255), `uuid` varchar(255), `status_tabela` TEXT, `dia` int(2), `mes` TEXT, `ano` int(4), `hora` int(2), `minutos` int(2))");
	      statement.executeUpdate("CREATE TABLE IF NOT EXISTS `loginsfirst` (`uuid` varchar(255), `username` varchar(255), `dia` int(2), `dia_da_semana` TEXT, `mes` TEXT, `ano` int(4), `hora` int(2), `minutos` int(2))");
	      statement.executeUpdate("CREATE TABLE IF NOT EXISTS `lastlogin` (`uuid` varchar(255), `username` varchar(255), `dia` int(2), `dia_da_semana` TEXT, `mes` TEXT, `ano` int(4), `hora` int(2), `minutos` int(2))");
	      statement.executeUpdate("CREATE TABLE IF NOT EXISTS `dados_punicoes` (`uuid` varchar(255), `username` varchar(255), `stats_ban` int(2), `motivo` varchar(255), `responsavel` varchar(255), `stats_mute` int(2), `motivo_mute` varchar(255), `responsavel_mute` varchar(255), `data_ban` varchar(255), `data_mute` varchar(255))");
	      plugin.database = true;
	    }
	  
	  public void closeDB() {
	    db.closeConnection();
	  }
	  
	  public static void firstJoinPlayer(Player p) throws SQLException {
	    if (!db.checkConnection()) {
	      db.openConnection();
	    }
	    Statement s = db.getConnection().createStatement();
	    
	    ResultSet rs = s.executeQuery("SELECT * FROM dados_lobby WHERE `uuid`='" + p.getUniqueId() + "';");
	    if (rs.next()) {
	      return;
	    }
	    s.executeUpdate("INSERT INTO dados_lobby (`uuid`, `username`, `senha`, `rank`, `xp`, `coins`, `verificado`, `staffchat`, `tell`) VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', 'Senha Não registrada', '" + 0 + "', '" + 0 + "', '" + 0 + "', 'NAO', '" + 0 + "', '" + 0 + "');");
	    s.executeUpdate("INSERT INTO dados_punicoes (`uuid`, `username`, `stats_ban`, `motivo`, `responsavel`, `stats_mute`, `motivo_mute`, `responsavel_mute`, `data_ban`, `data_mute`) VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', '" + 0 + "', 'NENHUM', 'NENHUM', '" + 0 + "', 'NENHUM', 'NENHUM', 'DATA_NAO_REGISTRADA', 'DATA_NAO_REGISTRADA');");
	    s.executeUpdate("INSERT INTO loginsfirst (`uuid`, `username`, `dia`, `dia_da_semana`, `mes`, `ano`, `hora`, `minutos`) VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', '"+ Main.getDia() + "', '" + Main.getDiaDaSemana() + "', '" + Main.getMeses() + "', '" + Main.getAno() + "', '" + Main.getHora() + "', '" + Main.getMinutos() +"');");
	    s.executeUpdate("INSERT INTO lastlogin (`uuid`, `username`, `dia`, `dia_da_semana`, `mes`, `ano`, `hora`, `minutos`) VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', '"+ Main.getDia() + "', '" + Main.getDiaDaSemana() + "', '" + Main.getMeses() + "', '" + Main.getAno() + "', '" + Main.getHora() + "', '" + Main.getMinutos() +"');");
	    Main.plugin.getLogger().log(Level.INFO,  "Os dados do jogador "+ p.getName() + "(" + p.getUniqueId() + ") foram inseridos com sucesso no banco de dados.");
	  }
	  
	  public static String getSenha(Player p) throws SQLException {
		String name = p.getUniqueId().toString();
		 if (!db.checkConnection()) {
		   db.openConnection();
		 }
		 Statement s = db.getConnection().createStatement();
		 ResultSet rs = s.executeQuery("SELECT * FROM dados_lobby WHERE `uuid`='" + name + "';");
		 if (!rs.next()) {
		   return "NAO";
		 }
		 String retorno = rs.getString("senha");
		    
		 return retorno;
	  }
	  public static String getMotivoMute(Player p) throws SQLException {
			String name = p.getUniqueId().toString();
			 if (!db.checkConnection()) {
			   db.openConnection();
			 }
			 Statement s = db.getConnection().createStatement();
			 ResultSet rs = s.executeQuery("SELECT * FROM dados_punicoes WHERE `uuid`='" + name + "';");
			 if (!rs.next()) {
			   return "NENHUM";
			 }
			 String retorno = rs.getString("motivo_mute");
			    
			 return retorno;
		  }
	  
	  public static String getResponsavelMute(Player p) throws SQLException {
			String name = p.getUniqueId().toString();
			 if (!db.checkConnection()) {
			   db.openConnection();
			 }
			 Statement s = db.getConnection().createStatement();
			 ResultSet rs = s.executeQuery("SELECT * FROM dados_punicoes WHERE `uuid`='" + name + "';");
			 if (!rs.next()) {
			   return "NENHUM";
			 }
			 String retorno = rs.getString("responsavel_mute");
			    
			 return retorno;
		  }
	  
	  public static String getDataBan(Player p) throws SQLException {
			String name = p.getUniqueId().toString();
			 if (!db.checkConnection()) {
			   db.openConnection();
			 }
			 Statement s = db.getConnection().createStatement();
			 ResultSet rs = s.executeQuery("SELECT * FROM dados_punicoes WHERE `uuid`='" + name + "';");
			 if (!rs.next()) {
			   return "NENHUMA";
			 }
			 String retorno = rs.getString("data_ban");
			    
			 return retorno;
		  }
	  
	  public static int getMute(Player p) throws SQLException {
			String name = p.getUniqueId().toString();
			 if (!db.checkConnection()) {
			   db.openConnection();
			 }
			 Statement s = db.getConnection().createStatement();
			 ResultSet rs = s.executeQuery("SELECT * FROM dados_punicoes WHERE `uuid`='" + name + "';");
			 if (!rs.next()) {
			   return 0;
			 }
			 int retorno = rs.getInt("stats_mute");
			    
			 return retorno;
		  }
	  
	  public static String getMotivo(Player p) throws SQLException {
			String name = p.getUniqueId().toString();
			 if (!db.checkConnection()) {
			   db.openConnection();
			 }
			 Statement s = db.getConnection().createStatement();
			 ResultSet rs = s.executeQuery("SELECT * FROM dados_punicoes WHERE `uuid`='" + name + "';");
			 if (!rs.next()) {
			   return "NENHUM";
			 }
			 String retorno = rs.getString("motivo");
			    
			 return retorno;
		  }
	  
	  public static String getResponsavel(Player p) throws SQLException {
			String name = p.getUniqueId().toString();
			 if (!db.checkConnection()) {
			   db.openConnection();
			 }
			 Statement s = db.getConnection().createStatement();
			 ResultSet rs = s.executeQuery("SELECT * FROM dados_punicoes WHERE `uuid`='" + name + "';");
			 if (!rs.next()) {
			   return "NENHUM";
			 }
			 String retorno = rs.getString("responsavel");
			    
			 return retorno;
		  }
	  
	  public static int getBan(Player p) throws SQLException {
			String name = p.getUniqueId().toString();
			 if (!db.checkConnection()) {
			   db.openConnection();
			 }
			 Statement s = db.getConnection().createStatement();
			 ResultSet rs = s.executeQuery("SELECT * FROM dados_punicoes WHERE `uuid`='" + name + "';");
			 if (!rs.next()) {
			   return 0;
			 }
			 int retorno = rs.getInt("stats_ban");
			    
			 return retorno;
		  }
	  
	  public static void updateSenha(Player p, String senhas) throws SQLException {
		String name = p.getUniqueId().toString();
		if (!db.checkConnection()) {
		  db.openConnection();
		}
	    Statement s = db.getConnection().createStatement();
		s.executeUpdate("UPDATE dados_lobby SET `senha`='" + senhas + "' WHERE `uuid`='" + name + "';");
	  }
}
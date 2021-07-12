package me.igormgs;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Comandos implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender parametroSender, Command parametroComando, String parametroLabel, String[] parametroArgumentos) {
		Player parametroJogador = (Player)parametroSender;
		if(parametroJogador instanceof Player) {
			if(parametroLabel.equalsIgnoreCase("registrar")) {
				try {
					if(MySQLManager.getSenha(parametroJogador).contains("Senha Não registrada")) {
						if(parametroArgumentos.length < 1) {
							parametroJogador.sendMessage("§a§lCADASTRO §fPara cadastrar-se em nossa rede, utilize /§b§lREGISTRAR§f, ou /§b§lREGISTER§f.");
							Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
							Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
							parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
							return false;
						} else if(parametroArgumentos.length > 1) {
							parametroJogador.sendMessage("§a§lCADASTRO §fPara cadastrar-se em nossa rede, utilize /§b§lREGISTRAR§f, ou /§b§lREGISTER§f.");
							Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
							Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
							parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
							
							return false;
						} else if(parametroArgumentos.length == 1) {
							if(parametroArgumentos[0] == null) {
								parametroJogador.sendMessage("§a§lCADASTRO §fPara cadastrar-se em nossa rede, utilize /§b§lREGISTRAR §f(§aSENHA§f), ou /§b§lREGISTER §f(§aSENHA§f)§f.");
								Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
								Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
								parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
								return false;
							} else {
								parametroJogador.sendMessage("§a§lCADASTRO §fCadastrado com sucesso. Senha cadastrada: §5" + parametroArgumentos[0] + "§f, Para mais detalhes digite /§6§lACCOUNT§f no lobby.");
								try {
									MySQLManager.updateSenha(parametroJogador, parametroArgumentos[0]);
								} catch (Exception e) {
									e.printStackTrace();
								}
								Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
								Framework.sendSubTitle(parametroJogador, "§fRegistrado com sucesso.");
								parametroJogador.playSound(parametroJogador.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
								Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
									
									@Override
									public void run() {
								        ByteArrayDataOutput out = ByteStreams.newDataOutput();
								        out.writeUTF("Connect");
								        out.writeUTF("LOBBY");
								        parametroJogador.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
									}
								}, 20L);
						        Main.logando.remove(parametroJogador);
								return true;
							}
						}
					} else {
						parametroJogador.sendMessage("§a§lCADASTRO §fVocê já está registrado em nossa Rede, utilize /§e§lLOGAR§f ou /§e§lLOGIN§f.");
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(parametroLabel.equalsIgnoreCase("register")) {
				try {
					if(MySQLManager.getSenha(parametroJogador).contains("Senha Não registrada")) {
						if(parametroArgumentos.length < 1) {
							parametroJogador.sendMessage("§a§lCADASTRO §fPara cadastrar-se em nossa rede, utilize /§b§lREGISTRAR§f, ou /§b§lREGISTER§f.");
							Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
							Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
							parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
							return false;
						} else if(parametroArgumentos.length > 1) {
							parametroJogador.sendMessage("§a§lCADASTRO §fPara cadastrar-se em nossa rede, utilize /§b§lREGISTRAR§f, ou /§b§lREGISTER§f.");
							Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
							Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
							parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
							return false;
						} else if(parametroArgumentos.length == 1) {
							if(parametroArgumentos[0] == null) {
								parametroJogador.sendMessage("§a§lCADASTRO §fPara cadastrar-se em nossa rede, utilize /§b§lREGISTRAR §f(§aSENHA§f), ou /§b§lREGISTER §f(§aSENHA§f)§f.");
								Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
								Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
								parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
								return false;
							} else {
								parametroJogador.sendMessage("§a§lCADASTRO §fCadastrado com sucesso. Senha cadastrada: §5" + parametroArgumentos[0] + "§f, Para mais detalhes digite /§6§lACCOUNT§f no lobby.");
								try {
									MySQLManager.updateSenha(parametroJogador, parametroArgumentos[0]);
								} catch (Exception e) {
									e.printStackTrace();
								}
								Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
								Framework.sendSubTitle(parametroJogador, "§fRegistrado com sucesso.");
								parametroJogador.playSound(parametroJogador.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
								Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
									
									@Override
									public void run() {
								        ByteArrayDataOutput out = ByteStreams.newDataOutput();
								        out.writeUTF("Connect");
								        out.writeUTF("LOBBY");
								        parametroJogador.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
									}
								}, 20L);
						        Main.logando.remove(parametroJogador);
								return true;
							}
						}
					} else {
						parametroJogador.sendMessage("§a§lCADASTRO §fVocê já está registrado em nossa Rede, utilize /§e§lLOGAR§f ou /§e§lLOGIN§f.");
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(parametroLabel.equalsIgnoreCase("logar")) {
				try {
					if(MySQLManager.getSenha(parametroJogador) == "Senha Não registrada") {
						parametroJogador.sendMessage("§a§lCADASTRO §fVocê não está registrado em nosso banco de dados, para se registrar utilize: /§e§lREGISTRAR§f, ou /§e§lREGISTER§f.");
						return false;
					} else {
						if(parametroArgumentos.length < 1) {
							parametroJogador.sendMessage("§a§lCADASTRO §fPara logar-se em nossa rede, utilize /§a§lLOGAR§f, ou /§a§lLOGIN§f.");
							Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
							Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
							parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
							return false;
						} else if(parametroArgumentos.length > 1) {
							parametroJogador.sendMessage("§a§lCADASTRO §fPara logar-se em nossa rede, utilize /§a§lLOGAR§f, ou /§a§lLOGIN§f.");
							Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
							Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
							parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
							return false;
						} else if(parametroArgumentos.length == 1) {
							if(parametroArgumentos[0] == null) {
								parametroJogador.sendMessage("§a§lCADASTRO §fPara logar-se em nossa rede, utilize /§e§lLOGAR §f(§aSENHA§f), ou /§e§lLOGIN §f(§aSENHA§f)§f.");
								Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
								Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
								parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
								return false;
							} else {
								String senha = MySQLManager.getSenha(parametroJogador);
								if(parametroArgumentos[0].equalsIgnoreCase(senha)) {
									parametroJogador.sendMessage("§a§lCADASTRO §fProcesso de Log-in efetuado com sucesso. Bom jogo.");
									Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
									Framework.sendSubTitle(parametroJogador, "§fProcesso de Log-in Efetuado.");
									parametroJogador.playSound(parametroJogador.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
									Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
										
										@Override
										public void run() {
									        ByteArrayDataOutput out = ByteStreams.newDataOutput();
									        out.writeUTF("Connect");
									        out.writeUTF("LOBBY");
									        parametroJogador.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
										}
									}, 20L);
							        Main.logando.remove(parametroJogador);
									return true;
								} else {
									parametroJogador.sendMessage("§a§lCADASTRO §fSenha incorreta.");
									Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
									Framework.sendSubTitle(parametroJogador, "§fSenha Incorreta.");
									parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
									return false;
								}
							}
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(parametroLabel.equalsIgnoreCase("login")) {
				try {
					if(MySQLManager.getSenha(parametroJogador) == "Senha Não registrada") {
						parametroJogador.sendMessage("§a§lCADASTRO §fVocê não está registrado em nosso banco de dados, para se registrar utilize: /§e§lREGISTRAR§f, ou /§e§lREGISTER§f.");
						return false;
					} else {
						if(parametroArgumentos.length < 1) {
							parametroJogador.sendMessage("§a§lCADASTRO §fPara logar-se em nossa rede, utilize /§a§lLOGAR§f, ou /§a§lLOGIN§f.");
							Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
							Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
							parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
							return false;
						} else if(parametroArgumentos.length > 1) {
							parametroJogador.sendMessage("§a§lCADASTRO §fPara logar-se em nossa rede, utilize /§a§lLOGAR§f, ou /§a§lLOGIN§f.");
							Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
							Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
							parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
							return false;
						} else if(parametroArgumentos.length == 1) {
							if(parametroArgumentos[0] == null) {
								parametroJogador.sendMessage("§a§lCADASTRO §fPara logar-se em nossa rede, utilize /§e§lLOGAR §f(§aSENHA§f), ou /§e§lLOGIN §f(§aSENHA§f)§f.");
								Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
								Framework.sendSubTitle(parametroJogador, "§fComando incorreto.");
								parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
								return false;
							} else {
								String senha = MySQLManager.getSenha(parametroJogador);
								if(parametroArgumentos[0].equalsIgnoreCase(senha)) {
									parametroJogador.sendMessage("§a§lCADASTRO §fProcesso de Log-in efetuado com sucesso. Bom jogo.");
									Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
									Framework.sendSubTitle(parametroJogador, "§fProcesso de Log-in Efetuado.");
									parametroJogador.playSound(parametroJogador.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
									Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
										
										@Override
										public void run() {
									        ByteArrayDataOutput out = ByteStreams.newDataOutput();
									        out.writeUTF("Connect");
									        out.writeUTF("LOBBY");
									        parametroJogador.sendPluginMessage(Main.plugin, "BungeeCord", out.toByteArray());
										}
									}, 20L);
							        Main.logando.remove(parametroJogador);
									return true;
								} else {
									parametroJogador.sendMessage("§a§lCADASTRO §fSenha incorreta.");
									Framework.sendTitle(parametroJogador, "§a§lCADASTRO");
									Framework.sendSubTitle(parametroJogador, "§fSenha Incorreta.");
									parametroJogador.playSound(parametroJogador.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
									return false;
								}
							}
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} else {
			System.out.printf("Apenas jogadores.");
			return false;
		}
		return false;
	}

}
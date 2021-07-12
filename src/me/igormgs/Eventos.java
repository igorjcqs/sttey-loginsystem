package me.igormgs;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("deprecation")
public class Eventos implements Listener {
	
	@EventHandler
	public void joinEvent(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		
		Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
			
			@Override
			public void run() {
				Main.logando.add(p);
			}
		}, 10L);
		
		try {
			MySQLManager.firstJoinPlayer(p);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Location l = new Location(p.getWorld(), -58.5, 64, 404.5);
		p.teleport(l);
		
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage(" ");
		p.sendMessage("§e§lINFO §fConectado ao servidor de §aLog-in§f.");
		p.sendMessage("§e§lINFO §fInstruções de Cadastro e Log-in a seguir:");
		p.sendMessage(" ");
		p.sendMessage("§fPara §b§l§nREGISTRAR§f sua conta utilize:");
		p.sendMessage(" ");
		p.sendMessage("§f/§b§lREGISTRAR §f(§eSUA SENHA§f) (§eSUA SENHA§f), ou");
		p.sendMessage("§f/§b§lREGISTER §f(§eSUA SENHA§f) (§eSUA SENHA§f).");
		p.sendMessage(" ");
		p.sendMessage("§fPara §a§l§nLOGAR§f sua conta utilize:");
		p.sendMessage(" ");
		p.sendMessage("§f/§a§lLOGIN §f(§eSENHA CADASTRADA§f), ou");
		p.sendMessage("§f/§a§lLOGAR §f(§eSENHA CADASTRADA§f).");
		p.sendMessage(" ");
		
		Framework.buildTabConstructor(p, "§c§m---§6§m---§e§m---§a§m---§3§m---§b§m---§5§m---§7§m[---]§5§m---§b§m---§3§m---§a§m---§e§m---§6§m---§c§m---§f\n\n§b§lSTTEY §f§lMC\n    §7Bem-vindo ao §aServidor de Login\n", "\n\n§7Site §astteymc.com\n§7Discord §ads.stteymc.com\n\n  §6§lPROMOÇãO §f§7em toda nossa §a§lLOJA§f§7!\nAcesse: §aloja.stteymc.com\n\n§c§m---§6§m---§e§m---§a§m---§3§m---§b§m---§5§m---§7§m[---]§5§m---§b§m---§3§m---§a§m---§e§m---§6§m---§c§m---§f");
		
		Framework.sendTitle(p, "§6§lBEM-VINDO");
		Framework.sendSubTitle(p, "§f" + p.getName());
		Framework.sendTimings(p, 10, 30, 10);
		
		Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
			
			@Override
			public void run() {
				Framework.sendTitle(p, "§a§lCADASTRO");
				Framework.sendSubTitle(p, "§fInstruções no CHAT.");
				Framework.sendTimings(p, 10, 30, 10);
			}
		}, 20L);
		
	}
	
	@EventHandler
	public void quitEvent(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}
	
	@EventHandler
	public void loginBanido(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		try {
			if(MySQLManager.getBan(p) == 1) {
				e.disallow(Result.KICK_BANNED, "§4§n§lPUNIDO!\n\n§fVocê foi §e§lPERMANENTIMENTE§f banido da rede §b§lSTTEY§f§lMC§f.\n\n§fMotivo: §3" + MySQLManager.getMotivo(p) + " \n§fStaff Responsável: §3" + MySQLManager.getResponsavel(p) + "\n§fData: §3" + MySQLManager.getDataBan(p) + "\n\n§fAcha que cometemos um §c§lERRO§f? \n§fSolicite §e§lAPPEAL §fem nosso discord: §awww.stteymc.com/discord\n§fou compre unban em nossa loja, §awww.stteymc.com/loja");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@EventHandler
	public void breakEvent(BlockBreakEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void placeEvent(BlockPlaceEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void falarNoChat(PlayerChatEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void moveEvent(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(Main.logando.contains(p)) {
			e.setCancelled(true);
		} else if(!Main.logando.contains(p)) {
			e.setCancelled(false);
		}
	}

}
package me.igormgs;

public class BlinkEffect {
	
	private int i = 1;
	private String 	texto = "§f§lCADASTRO";
	public BlinkEffect(){
		
	}
	
	public void next(){
		if (i == 1){
			texto = "§f§lC§b§lADASTRO";
		}
		if (i == 2){
			texto = "§b§lC§f§lA§b§lDASTRO";
		}
		if (i == 3){
			texto = "§b§lCA§f§lD§b§lASTRO";
		}
		if (i == 4){
			texto = "§b§lCAD§f§lA§b§lSTRO";
		}
		if (i == 5){
			texto = "§b§lCADA§f§lS§b§lTRO";
		}
		if (i == 6){
			texto = "§b§lCADAS§f§lT§b§lRO";
		}
		if (i == 7){
			texto = "§b§lCADAST§f§lR§b§lO";
		}
		if (i == 8){
			texto = "§b§lCADASTR§f§lO";
		}
		if (i == 9){
			texto = "§b§lCADASTRO";
		}
		if (i == 10){
			texto = "§b§l§nCADASTRO";
		}
		if (i == 11){
			texto = "§f§lCADASTRO";
		}
		if (i == 12){
			texto = "§f§l§nCADASTRO";
			i = 0;
		}
		i++;
	}
	public String getText(){
		return texto;
	}

}

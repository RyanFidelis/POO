package charlestest;

import robocode.*;
import java.awt.Color;
import java.util.Random;

public class Charlesrobot extends AdvancedRobot {

	private int padraoMovimento = 0;
	private int contadorPadrao = 0;
	private int contadorAcertos = 0;
	private double energiaInimigaAnterior = 100;
	private Random aleatorio = new Random();

	private static final int MARGEM = 60;

	public void run() {
		setColors(Color.red, Color.black, Color.green); // corpo, arma, radar
		setAdjustGunForRobotTurn(true);
		while (true) {
			evitarZonaVermelha();
			executarPadraoMovimento();
			turnGunRight(360);
			execute();
		}
	}

	private void evitarZonaVermelha() {
		double x = getX();
		double y = getY();
		double larguraCampo = getBattleFieldWidth();
		double alturaCampo = getBattleFieldHeight();

		if (x < MARGEM || x > larguraCampo - MARGEM || y < MARGEM || y > alturaCampo - MARGEM) {
			// Sai da borda
			turnRight(90);
			ahead(100);
		}
	}

	private void executarPadraoMovimento() {
		switch (padraoMovimento) {
			case 0: zigzag(); break;
			case 1: circuloDeslocamento(); break;
			case 2: vaiEVolta(); break;
			case 3: varreduraLateral(); break;
			case 4: movimentoEspiral(); break;
		}
		// Troca padrão depois de um tempo ou se for atingido
		if (++contadorPadrao >= 15 || contadorAcertos >= 3) {
			padraoMovimento = aleatorio.nextInt(5);
			contadorPadrao = 0;
			contadorAcertos = 0;
		}
	}

	private void zigzag() { ahead(40); turnRight(30); back(40); turnLeft(30); }
	private void circuloDeslocamento() { turnRight(5); ahead(50); }
	private void vaiEVolta() { ahead(60); back(60); }
	private void varreduraLateral() { turnRight(90); ahead(40); turnLeft(90); back(40); }
	private void movimentoEspiral() { turnRight(15); ahead(30 + aleatorio.nextInt(20)); }

	public void onScannedRobot(ScannedRobotEvent e) {
		double distancia = e.getDistance();
		double anguloRelativo = e.getBearing();
		double energiaInimiga = e.getEnergy();
		double anguloAbsoluto = getHeading() + anguloRelativo;
		double giroCanhao = normalizarAngulo(anguloAbsoluto - getGunHeading());

		setTurnGunRight(giroCanhao);

		// ver se o inimigo atirou
		boolean inimigoAgressivo = energiaInimigaAnterior > energiaInimiga && (energiaInimigaAnterior - energiaInimiga <= 3);
		energiaInimigaAnterior = energiaInimiga;

		// Muda de padrão baseado no inimigo
		if (inimigoAgressivo && distancia < 200) {
			padraoMovimento = 0; // zigzag
		} else if (!inimigoAgressivo && distancia > 300) {
			padraoMovimento = 1; // círculo
		} else {
			padraoMovimento = 3; // varredura lateral
		}

		// Ataques de distância
		if (distancia < 150) {
			fire(3);
			setAhead(40);
			setTurnRight(anguloRelativo + 20);
		} else if (distancia < 400) {
			fire(2);
			setTurnRight(anguloRelativo + 90);
			setAhead(50);
		} else {
			fire(1.5);
			setTurnRight(anguloRelativo);
			setBack(30);
		}

		// Robôs restantes
		System.out.println("Robôs restantes: " + getOthers());
	}

	public void onHitByBullet(HitByBulletEvent e) {
		contadorAcertos++;
		double angulo = e.getBearing();
		turnRight(90 - angulo);
		ahead(80);
	}

	public void onHitWall(HitWallEvent e) {
		back(60);
		turnRight(90);
	}

	private double normalizarAngulo(double angulo) {
		while (angulo > 180) angulo -= 360;
		while (angulo < -180) angulo += 360;
		return angulo;
	}
}

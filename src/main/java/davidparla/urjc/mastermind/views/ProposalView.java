package davidparla.urjc.mastermind.views;


import davidparla.urjc.mastermind.models.Game;
import davidparla.urjc.mastermind.models.ProposedCombination;
import davidparla.urjc.utils.WithConsoleView;

class ProposalView extends WithConsoleView {

	private Game game;

	private final SecretCombinationView secretCombinationView;

	ProposalView(Game game) {
		this.game = game;
		this.secretCombinationView = new SecretCombinationView();
	}

	boolean interact() {
		ProposedCombination proposedCombination = new ProposedCombination();
		ProposedCombinationView proposedCombinationView = new ProposedCombinationView(proposedCombination);
		proposedCombinationView.read();
		this.game.addProposedCombination(proposedCombination);
		this.console.writeln();
		MessageView.ATTEMPTS.writeln(this.game.getAttempts());
		this.secretCombinationView.writeln();
		for (int i = 0; i < this.game.getAttempts(); i++) {
			new ProposedCombinationView(this.game.getProposedCombination(i)).write();
			new ResultView(this.game.getResult(i)).writeln();
		}
		if (this.game.isWinner()) {
			MessageView.WINNER.writeln();
			return true;
		} else if (this.game.isLooser()) {
			MessageView.LOOSER.writeln();
			return true;
		}
		return false;
	}

}

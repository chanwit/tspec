package org.tspec.hangman;

import java.util.ArrayList;
import java.util.List;

public class Hangman {

	private String word;
	private int wrongs;
	private int maxGuess;
	private List<String> unrevealedWord;
	private boolean finished;

	public boolean isFinished() {
		return finished;
	}

	public List<String> getUnrevealedWord() {
		return unrevealedWord;
	}

	public int getMaxGuess() {
		return maxGuess;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
		unrevealedWord = new ArrayList<String>();
		for(int i=0;i<word.length();i++) {
			unrevealedWord.add("_");
		}
		this.maxGuess = 12;
		this.finished = false;
	}

	public int getWrongs() {
		return wrongs;
	}

	public void guess(String ch) throws ExceedGuessException {
		if(wrongs == 12) {
			throw new ExceedGuessException();
		}
		int i = this.word.indexOf(ch);
		if(i==-1) {
			wrongs++;
		} else {
			unrevealedWord.set(i, ch);
			for(int j=i+1;j<this.word.length();j++) {
				if(this.word.charAt(j)==ch.charAt(0)) {
					unrevealedWord.set(j, ch);
				}
			}
			finished = true;
			for(String s: unrevealedWord) {
				if(s.equals("_")) {
					finished = false;
					break;
				}
			}
		}
	}

	public void newGame() {
		this.wrongs = 0;
	}

}

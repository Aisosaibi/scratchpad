from board import Board
from game import Game
from player import Player
from symbol import Symbol


def test_that_assign_symbols_gives_first_player_x_by_default():
    board = Board()
    player1, player2 = Player("Player 1"), Player("Player 2")
    players = [player1, player2]
    game = Game(players, board, 5)
    game.assign_symbols()
    assert player1.get_symbol() == Symbol.X

def test_that_assign_symbols_gives_first_player_x_if_first_player_symbol_is_not_a_symbol():
    board = Board()
    player1, player2 = Player("Player 1"), Player("Player 2")
    players = [player1, player2]
    game = Game(players, board, 5)
    game.assign_symbols("not a symbol")
    assert player1.get_symbol() == Symbol.X

def test_that_assign_symbols_gives_second_player_the_other_symbol():
    board = Board()
    player1, player2 = Player("Player 1"), Player("Player 2")
    players = [player1, player2]
    game = Game(players, board, 5)
    game.assign_symbols(first_player_symbol=Symbol.O)
    assert player2.get_symbol() == Symbol.X

def test_that_get_current_player_recognizes_player_2_is_next():
    board = Board()
    player1, player2 = Player("Player 1"), Player("Player 2")
    players = [player1, player2]
    game = Game(players, board, 5)
    game.assign_symbols(first_player_symbol=Symbol.O)
    game.get_current_player()
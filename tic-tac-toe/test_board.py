# import pytest
from board import Board
from symbol import Symbol

# @pytest.fixture(autouse=True)
# def set_up():
#     return Board()

# @pytest.fixture
# def board():
#     return Board()

def test_that_find_spots_returns_list_of_all_spots():
    board = Board()
    spots = board.find_spots()
    assert spots == [[0, 0], [0, 1], [0, 2], [1, 0], [1, 1], [1, 2], [2, 0], [2, 1], [2, 2]]

def test_that_make_move_draws_symbol_in_available_spots():
    board = Board()
    board.make_move(0, 0, Symbol.X)
    num_available_spots = len(board.find_spots())
    assert num_available_spots == 8

def test_that_validate_move_returns_true():
    board = Board()
    spots = board.validate_move(0, 0, Symbol.X)
    assert spots == True

# def test_that_check_for_win_returns_true():
#     board = Board()
#     board.make_move(0, 0, Symbol.X)
#     board.make_move(0, 1, Symbol.X)
#     board.make_move(0, 2, Symbol.X)
#     assert board.check_for_win(Symbol.X) == True
#
# def test_that_check_for_win_returns_false():
#     board = Board()
#     board.make_move(0, 0, Symbol.X)
#     board.make_move(0, 1, Symbol.O)
#     board.make_move(0, 2, Symbol.X)
#     assert board.check_for_win(Symbol.O) == False


def test_that_check_for_win_returns_true_for_completed_row():
    board = Board()
    board.make_move(0, 0, Symbol.X)
    board.make_move(0, 1, Symbol.X)
    board.make_move(0, 2, Symbol.X)
    assert board.check_for_win() == True

def test_that_check_for_win_returns_false_when_no_win():
    board = Board()
    board.make_move(0, 0, Symbol.X)
    assert board.check_for_win() == False

def test_that_check_for_symbol_win_returns_true_for_correct_symbol_of_completed_row():
    board = Board()
    board.make_move(0, 0, Symbol.X)
    board.make_move(0, 1, Symbol.X)
    board.make_move(0, 2, Symbol.X)
    assert board.check_for_symbol_win(Symbol.X) == True

def test_that_check_for_symbol_win_returns_false_for_correct_symbol_of_completed_row():
    board = Board()
    board.make_move(0, 0, Symbol.X)
    board.make_move(0, 1, Symbol.X)
    board.make_move(0, 2, Symbol.X)
    assert board.check_for_symbol_win(Symbol.O) == False

def test_that_check_for_draw_returns_true_when_board_full_and_no_winner():
    board = Board()
    board.make_move(0, 0, Symbol.X)
    board.make_move(1, 1, Symbol.O)
    board.make_move(0, 2, Symbol.X)
    board.make_move(0, 1, Symbol.O)
    board.make_move(2, 1, Symbol.X)
    board.make_move(1, 0, Symbol.O)
    board.make_move(1, 2, Symbol.X)
    board.make_move(2, 2, Symbol.O)
    board.make_move(2, 0, Symbol.X)
    assert board.check_for_draw() == True

def test_that_check_for_draw_returns_false_when_board_has_open_spots():
    board = Board()
    board.make_move(0, 0, Symbol.X)
    board.make_move(0, 1, Symbol.O)
    board.make_move(0, 2, Symbol.X)
    assert board.check_for_draw() == False

def test_that_reset_board_clears_the_matrix():
    board = Board()
    board.make_move(0, 0, Symbol.X)
    board.make_move(1, 1, Symbol.O)
    board.make_move(0, 2, Symbol.X)
    board.make_move(0, 1, Symbol.O)
    board.make_move(2, 1, Symbol.X)
    board.make_move(1, 0, Symbol.O)
    board.make_move(1, 2, Symbol.X)
    board.make_move(2, 2, Symbol.O)
    board.make_move(2, 0, Symbol.X)
    board.reset_board()
    assert board.check_for_draw() == False
    assert board.check_for_win() == False
    assert board.find_spots() == [[0, 0], [0, 1], [0, 2], [1, 0], [1, 1], [1, 2], [2, 0], [2, 1], [2, 2]]


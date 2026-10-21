from player import Player
from symbol import Symbol

def test_that_set_name_changes_name():
    player = Player()
    player.set_name("Aisosaibi")
    assert player.get_name() == "Aisosaibi"

def test_that_get_name_returns_name():
    player = Player("Aisosaibi")
    assert player.get_name() == "Aisosaibi"

def test_that_select_symbol_assigns_player_symbol():
    player = Player("Aisosaibi")
    player.select_symbol(Symbol.X)
    assert player.get_symbol() == Symbol.X

def test_that_get_symbol_returns_none_if_symbol_not_assigned():
    player = Player("Aisosaibi")
    assert player.get_symbol() is None

def test_that_get_symbol_rejects_non_symbol():
    player = Player("Aisosaibi")
    player.select_symbol("banana")
    assert player.get_symbol() is None

def test_that_get_score_returns_zero():
    player = Player("Aisosaibi")
    assert player.get_score() == 0

def test_that_increment_score_increases_score_by_one():
    player = Player("Aisosaibi")
    player.increment_score()
    assert player.get_score() == 1

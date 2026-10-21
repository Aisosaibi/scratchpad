from decimal import Decimal

import pytest

from product import Product

def test_that_get_price_returns_correct_value():
    petrol1 = Product("Petrol", Decimal("400"))
    petrol2 = Product("Kerosene", Decimal("350"))
    assert petrol1.get_price() == Decimal("400")
    assert petrol2.get_price() == Decimal("350")

def test_that_negative_price_raises_value_error():
    with pytest.raises(ValueError):
        Product("Petrol", Decimal("-197"))

def test_that_get_name_returns_correct_value():
    petrol1 = Product("Petrol", Decimal("400"))
    petrol2 = Product("Kerosene", Decimal("400"))
    assert petrol1.get_name() == "Petrol"
    assert petrol2.get_name() == "Kerosene"

def test_that_set_price_raises_value_error_with_zero_input():
    petrol = Product("Petrol", Decimal("400"))
    with pytest.raises(ValueError):
        petrol.set_price(Decimal("0"))

def test_that_set_price_raises_value_error_with_negative_input():
    petrol = Product("Petrol", Decimal("400"))
    with pytest.raises(ValueError):
        petrol.set_price(Decimal("-100"))

def test_that_set_price_updates_price_value():
    petrol = Product("Petrol", Decimal("430"))
    petrol.set_price(Decimal("797"))
    assert petrol.get_price() == Decimal("797")

def test_that_convert_amount_to_liters_returns_correct_value():
    amount1 = Decimal("0")
    amount2 = Decimal("800")
    petrol = Product("Petrol", Decimal("400"))
    liters1 = petrol.convert_amount_to_liter(amount1)
    liters2 = petrol.convert_amount_to_liter(amount2)
    assert liters1 == amount1/petrol.get_price()
    assert liters2 == amount2/petrol.get_price()

def test_that_convert_liters_to_amount_returns_correct_value():
    liters1 = Decimal("0")
    liters2 = Decimal("10")
    petrol = Product("Petrol", Decimal("400"))
    price1 = petrol.convert_liters_to_amount(liters1)
    price2 = petrol.convert_liters_to_amount(liters2)
    assert price1 == liters1 * petrol.get_price()
    assert price2 == liters2 * petrol.get_price()

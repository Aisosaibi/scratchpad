import unittest

from product import Product
from decimal import Decimal

class TestProduct(unittest.TestCase):
    def test_that_get_price_returns_correct_value(self):
        petrol1 = Product("Petrol", Decimal("400"))
        petrol2 = Product("Kerosene", Decimal("365"))
        self.assertEqual(petrol1.get_price(), Decimal("400"))
        self.assertEqual(petrol2.get_price(), Decimal("365"))

    def test_that_negative_price_raises_value_error(self):
        with self.assertRaises(ValueError): # opens what python calls a context manager
            # with as used above: "for the duration of this indented block, watch for a ValueError"
            Product("Petrol", Decimal("-197"))

    # def test_that_negative_price_raises_value_error(self):
    #     self.assertRaises(ValueError, Product, "Petrol", Decimal("-197"))

    def test_that_get_name_returns_correct_value(self):
        petrol1 = Product("Petrol", Decimal("400"))
        petrol2 = Product("Kerosene", Decimal("365"))
        self.assertEqual(petrol1.get_name(), "Petrol")
        self.assertEqual(petrol2.get_name(), "Kerosene")

    def test_that_set_price_raises_value_error_with_zero_input(self):
        petrol1 = Product("Petrol", Decimal("400"))
        with self.assertRaises(ValueError):
            petrol1.set_price(Decimal("0"))

    def test_that_set_price_raises_value_error_with_negative_input(self):
        petrol1 = Product("Petrol", Decimal("400"))
        with self.assertRaises(ValueError):
            petrol1.set_price(Decimal("-100"))

    def test_that_set_price_updates_price_value(self):
        petrol = Product("Petrol", Decimal("430"))
        petrol.set_price(Decimal("797"))
        self.assertEqual(petrol.get_price(), Decimal("797"))

    def test_that_convert_amount_to_liters_returns_correct_value(self):
        amount1 = Decimal("0")
        amount2 = Decimal("800")
        petrol = Product("Petrol", Decimal("400"))
        liters1 = petrol.convert_amount_to_liter(amount1)
        liters2 = petrol.convert_amount_to_liter(amount2)
        self.assertEqual(liters1, (amount1 / petrol.get_price()))
        self.assertEqual(liters2, (amount2 / petrol.get_price()))

    def test_that_convert_liters_to_amount_returns_correct_value(self):
        liters1 = Decimal("0")
        liters2 = Decimal("10")
        petrol = Product("Petrol", Decimal("400"))
        price1 = petrol.convert_liters_to_amount(liters1)
        price2 = petrol.convert_liters_to_amount(liters2)
        self.assertEqual(price1, (liters1 * petrol.get_price()))
        self.assertEqual(price2, (liters2 * petrol.get_price()))

if __name__ == '__main__':
    unittest.main()
1
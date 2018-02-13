import unittest
from unittest.mock import Mock
from coffeemachine import CoffeeMachine
from coffeemachine import Drink

class CoffeeMachineTest(unittest.TestCase):

    def test_tea_with_1_sugar(self):
        message = CoffeeMachine().order(Drink.Tea,1,42)
        self.assertEqual(message, "T:1:0")

    def test_tea_with_2_sugar(self):
        message = CoffeeMachine().order(Drink.Tea,2,42)
        self.assertEqual(message, "T:2:0")

    def test_coffee_with_1_sugar(self):
        message = CoffeeMachine().order(Drink.Covfefe,1,1)
        self.assertEqual(message, "C:1:0")
        
    def test_chocolate_with_no_sugar(self):
        message = CoffeeMachine().order(Drink.Chocolate,0,1)
        self.assertEqual(message, "H::")

    def test_chocolate_with_one_sugar(self):
        message = CoffeeMachine().order(Drink.Chocolate,1,1)
        self.assertEqual(message, "H:1:0")
        
    def test_not_enough_money_provided(self):
        message = CoffeeMachine().order(Drink.Chocolate, 0, 0)
        self.assertEqual(message, "M:Missing 0.50")

    def test_enough_money_provided_to_buy_chocolate_with_no_sugar(self):
        message = CoffeeMachine().order(Drink.Chocolate, 0, 0.5)
        self.assertEqual(message, "H::")

    def test_enough_money_provided_to_buy_chocolate(self):
        message = CoffeeMachine().order(Drink.Chocolate, 1, 0.5)
        self.assertEqual(message, "H:1:0")

    def test_i_want_an_orangejuice(self):
        message = CoffeeMachine().order(Drink.OrangeJuice, 0, 0.6)
        self.assertEqual(message, "O::")

    def test_i_want_an_extra_hot_tea(self):
        message = CoffeeMachine().order(Drink.Chocolate, 0, 0.6)
        self.assertEqual(message, "C::")

    


if __name__ == '__main__':
    unittest.main()
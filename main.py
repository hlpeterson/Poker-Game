import pandas as pd
import pymysql
from sklearn import linear_model
from dotenv import load_dotenv
import os
import sys

load_dotenv()

my_python_key = os.getenv('PASSWORD')

conn = pymysql.connect(
    host='localhost',
    user='root',
    password=f"{my_python_key}",
    db='poker_data',
)

regr = linear_model.LinearRegression()

preflop_input = sys.argv[1]
flop_input = sys.argv[2]
turn_input = sys.argv[3]
river_input = sys.argv[4]


def predict_hand(preflop, flop, turn, river):
    predicted_hand = regr.predict([[preflop, flop, turn, river]])
    return predicted_hand


SQL_Query = pd.read_sql_query('SELECT * FROM poker_data', conn)
df = pd.DataFrame(SQL_Query, columns=['id', 'preflop', 'flop', 'turn', 'river', 'value'])
X = df[['preflop', 'flop', 'turn', 'river']]
y = df['value']
regr.fit(X, y)
print(predict_hand(int(preflop_input), int(flop_input), int(turn_input), int(river_input)))

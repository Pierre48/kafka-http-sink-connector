import flask
from flask import request, jsonify,abort

app = flask.Flask(__name__)
app.config["DEBUG"] = True

items = [
    ]

@app.route('/', methods=['GET'])
def homeGet():
    return jsonify(items)

@app.route('/', methods=['POST'])
def homePost():
    if not request.json:
        abort(400)
    id=len(items)
    item= {'Id': id, 'Name':'item'+str(id)}
    items.append(item)
    return jsonify({'item': item}), 201

app.run()

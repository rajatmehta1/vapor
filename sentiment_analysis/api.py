from flask import Flask
from flask_restful import Resource, Api
from generate_classifier import load_classifier, most_common
from utils import normalize
import pickle
import requests

#from generate_classifier import load_classifier,

app = Flask( __name__ )
api = Api( app )

def get_classifier_from_url( url ):
    response = requests.get( url ).content
    
    with open( "tmp_file", "wb" ) as handler:
        handler.write( response )

    with open( "tmp_file", "rb" ) as handler:
        return pickle.load( handler )

        
def extract_features( doc ):
    """ add documentation """
    features = { word: True for word in most_common if word in doc }
    return features
        

#clf = get_classifier_from_url( url = "https://s3.amazonaws.com/sentiment-classifier/classifier2" )

clf = load_classifier()

class HelloWorld( Resource ):
    def get( self ):
        return { 'hello': 'world' }

class Classify( Resource ):

    def get( self, query ):
        print( query )
        query_s = ' '.join( query.split('+') )
        sent = clf.classify( extract_features( normalize( query_s ) ) )
        return { 'sent': sent }
        
api.add_resource( Classify, '/<string:query>' )
api.add_resource( HelloWorld, '/' )

if __name__ == '__main__':
    app.run( debug=False, port = 80 )
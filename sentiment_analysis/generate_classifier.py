import csv
import pickle
from utils import normalize
from nltk.tokenize import RegexpTokenizer
import nltk.classify
from nltk import FreqDist

def extract_features( doc ):
    """ add documentation """
    features = { word: True for word in most_common if word in doc }
    return features

def save_classifier( classifier, file_name ):
    """ saves NaiveBayesClassifier object to file_name """
    with open( file_name, 'wb') as file_handle:
        pickle.dump( classifier, file_handle )
        
def load_classifier( file_name = "classifier" ):
    """ loads the classifier from file_name """
    with open( file_name, 'rb') as file_handle:
        return pickle.load( file_handle )
        

with open('C:/Users/Benjamin/Downloads/trainingandtestdata/pos_words.csv') as csv_file:
    reader = csv.reader( csv_file, delimiter = ',' )
    pos_words = [ normalize( row[ 1 ] ) for row in reader ]

with open('C:/Users/Benjamin/Downloads/trainingandtestdata/neg_words.csv') as csv_file:
    reader = csv.reader( csv_file, delimiter = ',' )
    neg_words = [ normalize( row[ 1 ] ) for row in reader ]
    
all_words = pos_words + neg_words
word_features = [ word for wordList in all_words for word in wordList ]
    
most_common = [ word for word, _ in FreqDist( word_features ).most_common()[ :3000 ] ]

def main():
    """ add documentation """
    
    pos_feats = [ ( extract_features( doc ), 'pos' ) for doc in pos_words ]
    neg_feats = [ ( extract_features( doc ), 'neg' ) for doc in neg_words ]
    
    feature_set = pos_feats + neg_feats
    n = len( feature_set ) * 3//4
    train_set, test = feature_set[ :n ], feature_set[ n: ]
    
    classifier = nltk.classify.NaiveBayesClassifier.train( train_set )
    
    print( 'accuracy:', nltk.classify.util.accuracy( classifier, test ) )
    
    classifier.show_most_informative_features()
    
    #tests
    test_feedback = [ ( "This is so awful. Who thought this was a good idea?", "neg" ), ("I love JP Morgan's new feedback system", "pos" ),
                ( "I do not like it", "neg" ), ( "This is a very good system", "pos" ), ( "I'm very impressed with this", "pos" ) ]
    for query, sent in test_feedback:
        # #assert( classifier.classify( extract_features( normalize( query ), most_common ) ) == sent )
        assert( classifier.classify( extract_features( normalize( query ) ) ) == sent )
    save_classifier( classifier, ".\classifier2" )

if __name__ == "__main__":
    main()
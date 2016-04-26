from nltk.tokenize import RegexpTokenizer

def normalize( tweet ):
    """ lowercases, tokenizes and removes @ mentions """
    tokenizer = RegexpTokenizer( r'\w+' )
    tweet_tokenized = tokenizer.tokenize( tweet.lower() )
    return [ word for word in tweet_tokenized if ( ( len( word ) > 1 ) and ('@' not in word ) ) ]
var google = require('googleapis');
var trainedmodels = google.prediction('v1.6').trainedmodels;

function auth (callback) {
  google.auth.getApplicationDefault(function (err, authClient) {
    if (err) {
      return callback(err);
    }

    // The createScopedRequired method returns true when running on GAE or a
    // local developer machine. In that case, the desired scopes must be passed
    // in manually. When the code is  running in GCE or GAE Flexible, the scopes
    // are pulled from the GCE metadata server.
    // See https://cloud.google.com/compute/docs/authentication for more
    // information.
    if (authClient.createScopedRequired && authClient.createScopedRequired()) {
      // Scopes can be specified either as an array or as a single,
      // space-delimited string.
      authClient = authClient.createScoped([
        'https://www.googleapis.com/auth/prediction'
      ]);
    }
    callback(null, authClient);
  });
}


phrase = "2016 25 0 1 5 1453728037";
auth(function (err, authClient) {
    if (err) {
        return callback(err);
    }
    // Predict the sentiment for the provided phrase
    trainedmodels.predict({
        auth: authClient,
        // Project id used for this sample
        project: 'psychic-ruler-156319',
        id: 'menupredictor',
        resource: {
            input: {
                       // Predict sentiment of the provided phrase
                       csvInstance: phrase.split(/\s/gi)
                   }
        }
    }, function (err, prediction) {
        if (err) {
            return callback(err);
        }
        // Received prediction result
        console.log( prediction.outputLabel );
    });
});


function  [centroids,idx]=Kmeans(X)
K = 3;
max_iters = 100;


initial_centroids = kMeansInitCentroids(X,K);

[centroids, idx] = runkMeans(X, initial_centroids, max_iters);
function idx = findClosestCentroids(X, centroids)


% Set K
K = size(centroids, 1);

% You need to return the following variables correctly.
idx = zeros(size(X,1), 1);

for i=1:size(X,1)
    min=sum((centroids(1,:)-X(i,:)).^2);
    mini=1;
    for j=2:K
        if(sum((centroids(j,:)-X(i,:)).^2)<min)
            min=sum((centroids(j,:)-X(i,:)).^2);
            mini=j;
        end
    end
    idx(i)=mini;
end






% =============================================================

end


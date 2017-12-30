function centroids = computeCentroids(X, idx, K)


[m n] = size(X);

centroids = zeros(K, n);



for i=1:K
    tem=zeros(1,n);
    co=0;
    for j=1:m
        if(idx(j)==i)
            tem=tem+X(j,:);
            co=co+1;
        end
    end
    centroids(i,:)=tem./co;
end







% =============================================================


end


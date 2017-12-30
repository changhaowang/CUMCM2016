function w=calW(W)
 for i=1:length(W)
     for j=1:length(W)
         w(i,j)=W(i)/(W(i)+W(j));
     end
 end
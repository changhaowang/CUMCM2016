function f=calF(r)
  s=sum(r,2);
  for i=1:size(r,1)
      f(i,:)=r(i,:)/s(i);
  end
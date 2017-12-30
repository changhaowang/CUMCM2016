function g=G(k)
for i=1:length(k)
  if(k(i)<0.5*25),
      g(i)=k(i)*500;
  else
      g(i)=exp(-(k(i)-0.5*25)/25).*k(i)*500;
  end
end
end

      
      
  
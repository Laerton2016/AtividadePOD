# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM maven:3.3.9-jdk-8

# ----------                                                                                                 
# OPERATIONS                                                                                                  
# ----------                                                                                   
ADD . /opt/app
RUN cd /opt/app && mvn compile package -DskipTests=true
RUN chmod +x /opt/app/start.sh

CMD touch /opt/shared/sum.txt && touch /opt/shared/diff.txt && /opt/app/start.sh

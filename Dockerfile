FROM node:4.4.5

ADD . /src

RUN npm install forever -g

WORKDIR /src
ADD package.json /src/package.json
RUN npm install

EXPOSE 3000

CMD npm start
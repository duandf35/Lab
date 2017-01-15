
module CanRun

  # class << self open up a singleton class
  # consider the method inside the block as a static method
  # same as defining self.included(base)
  class << self

    # Define the <included> method for inherit static method from
    # another class/module automatically when mixin happened
    #
    # When any class/module include this module, it will automatically
    # inherit the Runnable module
    #
    # <include> module => instance method
    # <extend>  module => static method
    # see also:
    # http://www.railstips.org/blog/archives/2009/05/15/include-vs-extend-in-ruby/
    def included(base)
      base.extend Runnable
    end
  end

  module Runnable
    def static_check
      puts "(static) Inherit <static_check> method from <Runnable> when Mixin <CanRun> to class <#{self.class}>"
    end
  end

  def run
    puts "(instance) Execute <run> in class <#{self.class}>"
  end
end
